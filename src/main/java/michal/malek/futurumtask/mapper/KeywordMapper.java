package michal.malek.futurumtask.mapper;

import michal.malek.futurumtask.model.entity.Keyword;
import michal.malek.futurumtask.model.request.KeywordRequest;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface KeywordMapper {
    Keyword map(KeywordRequest keywordRequest);
    List<Keyword> map(List<KeywordRequest> keywordRequestList);
}
