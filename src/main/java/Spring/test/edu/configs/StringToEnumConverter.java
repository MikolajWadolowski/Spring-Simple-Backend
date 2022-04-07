package Spring.test.edu.configs;

import Spring.test.edu.models.Genre;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.EnumSet;

@Converter
public class StringToEnumConverter implements AttributeConverter<EnumSet<Genre>, String> {

    public String convertToDatabaseColumn(EnumSet<Genre> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Genre c : attribute) {

            sb.append(c + ",");
        }
        return sb.toString();
    }

    public EnumSet<Genre> convertToEntityAttribute(String dbData) {
        EnumSet<Genre> genre = EnumSet.noneOf(Genre.class);
        if (dbData == null || dbData.isEmpty()) {
            return genre;
        }
        String[] genres = StringUtils.trimAllWhitespace(dbData).toUpperCase().split(",");
        if (genres.length != 0) {
            try {
                for (String str : genres) {
                    genre.add(Genre.valueOf(str));
                }
            } catch (IllegalArgumentException IAE) {

            }
        }
        return genre;

    }

}