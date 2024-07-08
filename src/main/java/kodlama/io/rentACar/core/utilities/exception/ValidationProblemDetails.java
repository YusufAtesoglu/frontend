package kodlama.io.rentACar.core.utilities.exception;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValidationProblemDetails extends ProblemDetailes{

	private Map<String,String> validationErrors;
}
