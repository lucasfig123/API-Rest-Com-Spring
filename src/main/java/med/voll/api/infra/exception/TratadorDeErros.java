package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Anotação específica para classes de tratamento de erros(exceptions)
@RestControllerAdvice
public class TratadorDeErros {

    //Anotação que identifica para qual exceção esse método irá ser chamado
    //Metodo que retorna a Exception de erro 404 porém sem nenhum mensagem no body, não é necessário nesse caso
//    @ExceptionHandler(EntityNotFoundException.class)
//    public ResponseEntity tratarErro404(){
//        return ResponseEntity.notFound().build();
//    }

    // O mesmo código que o de cima porém esse traz uma mensagem no corpo da requisição
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(EntityNotFoundException ex){
        var erros = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erros);
    }


    //metodo de Exception que trata os erros de 400(bad request), onde eu passo o próprio exception,
    // como parametro MethodArgumentNotValidException para receber o corpo da exceção
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex){
        var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacao::new).toList());
    }

    //metodo utilizado para filtrar apenas as informações necessárias na saída, por exemplo no POST do Insomnia:
    //[
    //	{
    //		"campo": "crm",
    //		"mensagem": "não deve estar em branco"
    //	},
    //	{
    //		"campo": "telefone",
    //		"mensagem": "não deve estar em branco"
    //	},
    //	{
    //		"campo": "email",
    //		"mensagem": "não deve estar em branco"
    //	},
    //	{
    //		"campo": "nome",
    //		"mensagem": "não deve estar em branco"
    //	}
    //]
    private record DadosErroValidacao(String campo, String mensagem){
        public DadosErroValidacao(FieldError erro){
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
