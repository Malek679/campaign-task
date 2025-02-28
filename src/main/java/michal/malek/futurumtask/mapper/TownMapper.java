package michal.malek.futurumtask.mapper;

import michal.malek.futurumtask.model.entity.Town;
import michal.malek.futurumtask.model.request.TownRequest;
import michal.malek.futurumtask.model.response.TownResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TownMapper {
    Town ResponsetoTown(TownResponse townResponse);
    Town RequestToTown(TownRequest townRequest);
}
