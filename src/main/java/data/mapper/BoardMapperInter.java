package data.mapper;

import data.dto.BoardDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


@Mapper
public interface BoardMapperInter {
    @Insert("""
            insert into board (nickname,myid,title,content,photo,writeday) values (#{nickname},#{myid},#{title},#{content},#{photo},now())
            """)
    public void insertBoard(BoardDto dto);

    public int getMaxNum();

    public BoardDto getData(int num);

    public List<BoardDto> getPagingList(Map<String,Integer> map);

    public int getTotalCount();

    public void updateReadcount(int num);
}
