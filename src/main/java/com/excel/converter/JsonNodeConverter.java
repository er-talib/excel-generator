package com.excel.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import org.springframework.util.StringUtils;


import java.io.IOException;

@Converter
public class JsonNodeConverter implements AttributeConverter<JsonNode, String>
{
    @Override
    public String convertToDatabaseColumn(JsonNode jsonNode){
        if( jsonNode == null) {
            return null;
        }

        String jsonNodeString = jsonNode.toString();
        return jsonNodeString;
    }
    @Override
    public JsonNode convertToEntityAttribute(String jsonNodeString) {

        if ( StringUtils.isEmpty(jsonNodeString) ){
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readTree( jsonNodeString );
        }
        catch( JsonProcessingException e )
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}