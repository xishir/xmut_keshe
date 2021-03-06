package com.xmut.dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xmut.pojo.TagInfo;
@Mapper
public interface TagMapper {
	
	@Insert("INSERT INTO k_tag(tag) VALUES (#{tag})")
    void createtag(@Param("tag") String tag);
	
	@Delete("DELETE FROM k_tag WHERE id = #{id} or tag=#{tag}")
    void deleteTag(TagInfo taginfo);
	
	@Select("select distinct tag FROM k_tag ")	
    ArrayList<String> getSort();
	
	@Select("select tag,num FROM k_tag ")	
    ArrayList<TagInfo> getSorts();
	
	@Select("select * FROM k_tag where id=#{id}")
	TagInfo getTag(@Param("id") String id);
	
	@Update("UPDATE k_tag SET num = num+1 WHERE tag = #{tag}")
    void addNum(@Param("tag") String tag);
	
	@Update("UPDATE k_tag SET tag = #{tag} WHERE id = #{id}")
    void updataTag(@Param("tag") String tag,@Param("id") String id);
	
	@Select("select count(*) FROM k_tag ")
    String getTagNum();

}
