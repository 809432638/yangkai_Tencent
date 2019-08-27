package com.yangkai.cms.utils;

/**
 * 
 * @ClassName: TileEnum 
 * @Description: 枚举实现类
 * @author: 阿里人:杨某 
 * @date: 2019年7月31日 上午10:37:56
 */
public enum TileEnum  {
	
	COLOR_RED("color:red","R(标题红色)"),FONTWEIGHT("font-weight:800","B(标题加粗)"),
	FONTSTYLE("font-style:italic","I(标题斜体)"),STYLE_NONE("text-decoration:none;color:inherit","无样式");
	
	private String code;
	private String value;
	TileEnum(String code, String value) {
		this.code = code;
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	/*public static void main(String[] args) {
		TileEnum[] enums = TileEnum.values();
		for (TileEnum e : enums) {
			System.out.println(e.getCode() +":"+e.getValue());
		}
		System.out.println();
	}*/

}
