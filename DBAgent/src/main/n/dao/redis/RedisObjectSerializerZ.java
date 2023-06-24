package comApp.dao.redis;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.serializer.support.DeserializingConverter;
import org.springframework.core.serializer.support.SerializingConverter;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

/**
 * @author zzz
 * @Date 07/06/2023
 */
public class RedisObjectSerializerZ implements RedisSerializer<Object> {
    Converter<Object,byte[]> serializer= new SerializingConverter();
    Converter<byte[], Object> deserializer = new DeserializingConverter();

    @Override
    public byte[] serialize(Object o) throws SerializationException {
        
        return serializer.convert(o);
        
//        return new byte[0];
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        assert bytes != null;
        
//        if (bytes.getClass()) {
//            
//        }
        return deserializer.convert(bytes);
        
//        return null;
    }
}
