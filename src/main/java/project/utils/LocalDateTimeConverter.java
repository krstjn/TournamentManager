package project.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * Helper class to be able to use LocalDateTime
 * in the Database
 */
@Converter(autoApply = true)
public class LocalDateTimeConverter implements AttributeConverter<java.time.LocalDateTime, java.sql.Timestamp> {

    /*
        Assists with persisting LocalDateTime to the DB
     */
    @Override
    public java.sql.Timestamp convertToDatabaseColumn(java.time.LocalDateTime attribute) {

        return attribute == null ? null : java.sql.Timestamp.valueOf(attribute);
    }

    @Override
    public java.time.LocalDateTime convertToEntityAttribute(java.sql.Timestamp dbData) {

        return dbData == null ? null : dbData.toLocalDateTime();
    }
}
