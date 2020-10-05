package com.gn5r.spring.boot.common.resource;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * SelectBox用リソース
 * </p>
 *
 * <ul>
 * <li>id - SelectBoxで選択された時に格納される値</li>
 * <li>value - SelectBoxに表示されるテキストラベル</li>
 * </ul>
 *
 * @author gn5r
 * @since 0.1.0-RELEASE
 */
@lombok.Data
@ApiModel(description = "SelectBox用リソース")
public final class SelectBoxResource implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "id", notes = "SelectBoxで選択される値")
	private String id;

	@ApiModelProperty(value = "value", notes = "SelectBoxで表示される値")
	private String value;
}