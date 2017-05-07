/**
 * 
 */
package cn.edu.sdut.softlab.validator;

import javax.annotation.Resource;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * @author gaoyisheng
 *
 */
public class StdValidator {
	//参考Pro JPA2 (page 336)
	
	//检查 传入的实体类数据 符合不符合 数据库的限制  ：
	/*
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Basic(optional = false)
  @Column(name = "id")
	 */
	
	//javax.validation.Validator;包下的validation
	//yongfa
	//	I
	@Resource
	Validator v1;
	//v.validate(entity);   
	
	// II
	ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
	Validator v = vf.getValidator();
}
