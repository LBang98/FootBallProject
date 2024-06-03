package data.mapper;

import data.dto.BoardDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BoardMapperInter {
    @Insert("""
            insert into board (num,nickname,title,content,writeday) values (#{num},#{nickname},#{title},#{content},now())
            """)
    public void insertAnswer(BoardDto dto);

    @Select("select * from board where num=#{num} order by aidx desc")
    public List<BoardDto> getAnswerData(int num);

    @Delete("delete from board where aidx=#{aidx}")
    public void deleteAnswer(int aidx);
}
