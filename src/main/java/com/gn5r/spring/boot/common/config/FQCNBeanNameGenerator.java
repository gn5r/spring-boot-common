package com.gn5r.spring.boot.common.config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * Bean登録名をパッケージ名と紐づけるクラス
 *
 * @author gn5r
 * @since 0.1.0-RELEASE
 * @see AnnotationBeanNameGenerator
 */
public final class FQCNBeanNameGenerator extends AnnotationBeanNameGenerator {

	/**
	 * Bean登録時にパッケージ名を付与した名称を返却する
	 *
	 * @param definition BeanDefinition
	 * @return パッケージ名付きBean名
	 */
	@Override
	public String buildDefaultBeanName(BeanDefinition definition) {
		return definition.getBeanClassName();
	}
}