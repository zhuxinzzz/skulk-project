//package com.dao.redis;
//
//
///**
// * @author zzz
// * @Date 07/06/2023
// */
//public class RedisObjectSerializerZ implements RedisSerializer<Object> {
//    Converter<Object,byte[]> serializer= new SerializingConverter();
//    Converter<byte[], Object> deserializer = new DeserializingConverter();
//
//    @Override
//    public byte[] serialize(Object o) throws SerializationException {
//
//        return serializer.convert(o);
//
////        return new byte[0];
//    }
//
//    @Override
//    public Object deserialize(byte[] bytes) throws SerializationException {
//        assert bytes != null;
//
////        if (bytes.getClass()) {
////
////        }
//        return deserializer.convert(bytes);
//
////        return null;
//    }
//}
