package net.realme.mall.store.config;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.messageinterpolation.ResourceBundleMessageInterpolator;
import org.hibernate.validator.resourceloading.PlatformResourceBundleLocator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@Configuration
public class ValidatorConfiguration {

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		MethodValidationPostProcessor postProcessor = new MethodValidationPostProcessor();
		postProcessor.setValidator(validator());
		return postProcessor;
	}

	@Bean
	public Validator validator() {
		HibernateValidatorConfiguration configure = Validation.byProvider(HibernateValidator.class).configure();
		/** 设置模式为快速失败返回 */
		configure.failFast(true);
		/** 设置校验国际化文件 */
		configure.messageInterpolator(
				new ResourceBundleMessageInterpolator(new PlatformResourceBundleLocator("validation")));
		ValidatorFactory validatorFactory = configure.buildValidatorFactory();
		Validator validator = validatorFactory.getValidator();
		return validator;
	}
}
