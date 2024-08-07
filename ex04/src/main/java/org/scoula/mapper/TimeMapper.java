package org.scoula.mapper;
import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
//    @Select 어노테이션을 사용해서 SQL문을 직접 지정
@Select("SELECT sysdate FROM dual")
    public String getTime();
// 데이터베이스에서 현재 시스템 날짜와 시간을 반환
    public String getTime2();
}