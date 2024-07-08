package kodlama.io.rentACar;

import java.util.HashMap;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kodlama.io.rentACar.core.utilities.exception.BusinessException;
import kodlama.io.rentACar.core.utilities.exception.ProblemDetailes;
import kodlama.io.rentACar.core.utilities.exception.ValidationProblemDetails;

@SpringBootApplication
@RestControllerAdvice
public class RentACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentACarApplication.class, args);
	}
	
	@ExceptionHandler                           //hata mesajlarını gizlemek için
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ProblemDetailes handleBusinessException(BusinessException businessException) {
		ProblemDetailes problemDetails =new ProblemDetailes();
		problemDetails.setMessage(businessException.getMessage());
		
		return problemDetails;
	}

	
	@ExceptionHandler                           //hata mesajlarını gizlemek için
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	public ProblemDetailes handleValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {
		ValidationProblemDetails validationProblemDetails =new ValidationProblemDetails();
		validationProblemDetails.setMessage("VALİDATİON.EXCEPTION");
		validationProblemDetails.setValidationErrors(new HashMap<String,String>());
		
		for(FieldError fieldError: methodArgumentNotValidException.getBindingResult().getFieldErrors()) {
			validationProblemDetails.getValidationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		return validationProblemDetails ;
	}
	
	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	
}
