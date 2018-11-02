package io.ushi.rest;

import lombok.Data;

import java.util.List;

/**
 * Query参数使用Json传递，格式如下，关键字使用美元符号($)开头，均仿SQL，不过仅使用常用关键字，并非SQL的完整语法。
 * {
 * 	"$select": "field1, field2, field3",
 * 	"$where": {
 * 	    "field1": "<=9",
 * 	    "$or": {
 * 	        "field2": "%contains%",
 * 	        "field3": "equal"
 *      }
 *  },
 * 	"$limit": "10, 10",
 * 	"$sort": "-field1, field3"
 * }
 */
@Data
public class QueryEntity {

    List<String> selections;

    String code;

    String message;


}
