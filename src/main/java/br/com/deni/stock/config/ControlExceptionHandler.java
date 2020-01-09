package br.com.deni.stock.config;

import br.com.deni.stock.exception.BusinessException;
import br.com.deni.stock.exception.ExceptionResolver;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ControlExceptionHandler {


	public static final String X_RD_TRACEID = "X-rd-traceid";
	public static final String CONSTRAINT_VALIDATION_FAILED = "Constraint validation failed";

	@ExceptionHandler(value = { BusinessException.class})
	protected ResponseEntity<Object> handleConflict(BusinessException ex, WebRequest request) {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(X_RD_TRACEID,this.getTraceID());
		return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());

	}

	@ExceptionHandler({ Throwable.class })
	public ResponseEntity<Object> handleException(Throwable eThrowable) {

		BusinessException ex = BusinessException.builder()
				.httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR)
				.message(Optional.ofNullable(eThrowable.getMessage()).orElse(eThrowable.toString()))
				.description(ExceptionResolver.getRootException(eThrowable))
				.build();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(X_RD_TRACEID,this.getTraceID());

		return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());

	}

	/**
	 *
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException exMethod,
																   WebRequest request) {

		String error = exMethod.getName() + " should be " + exMethod.getRequiredType().getName();

		BusinessException ex = BusinessException.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST)
				.message(CONSTRAINT_VALIDATION_FAILED)
				.description(error)
				.build();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(X_RD_TRACEID,this.getTraceID());

		return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
	}

	/**
	 *
	 * @param exMethod
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ ConstraintViolationException.class })
	public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exMethod, WebRequest request) {
		List<String> errors = new ArrayList<String>();
		for (ConstraintViolation<?> violation : exMethod.getConstraintViolations()) {
			errors.add(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": "
					+ violation.getMessage());
		}

		BusinessException ex = BusinessException.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST)
				.message(CONSTRAINT_VALIDATION_FAILED)
				.description(errors.toString())
				.build();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(X_RD_TRACEID,this.getTraceID());

		return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
	}

	/**
	 *
	 * @param exMethod
	 * @return
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> validationError(MethodArgumentNotValidException exMethod) {

		BindingResult bindingResult = exMethod.getBindingResult();

		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		List<String> fieldErrorDtos = fieldErrors.stream()
				.map(f -> f.getField().concat(":").concat(f.getDefaultMessage())).map(String::new)
				.collect(Collectors.toList());

		BusinessException ex = BusinessException.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST)
				.message(CONSTRAINT_VALIDATION_FAILED)
				.description(fieldErrorDtos.toString())
				.build();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(X_RD_TRACEID,this.getTraceID());

		return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
	}

	/**
	 *
	 * @param exMethod
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> validationError(HttpMessageNotReadableException exMethod) {


		Throwable mostSpecificCause = exMethod.getMostSpecificCause();
		String message = null;
		if (mostSpecificCause != null) {
			String exceptionName = mostSpecificCause.getClass().getName();
			message = mostSpecificCause.getMessage();
		} else {
			message = exMethod.getMessage();
		}
		BusinessException ex = BusinessException.builder()
				.httpStatusCode(HttpStatus.BAD_REQUEST)
				.message(CONSTRAINT_VALIDATION_FAILED)
				.description(message)
				.build();
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.set(X_RD_TRACEID,this.getTraceID());

		return ResponseEntity.status(ex.getHttpStatusCode()).headers(responseHeaders).body(ex.getOnlyBody());
	}

	private String getTraceID(){
		String traceId = Optional.ofNullable(MDC.get(UniqueTrackingNumberFilter.TRACE_ID_KEY)).orElse("");
		traceId = Optional.ofNullable(traceId.trim().isEmpty()?null:traceId).orElse("not available");

		return traceId;
	}
}
