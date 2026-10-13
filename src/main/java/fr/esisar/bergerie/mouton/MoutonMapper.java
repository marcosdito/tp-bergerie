package fr.esisar.bergerie.mouton;

import org.mapstruct.*;

@Mapper(componentModel = "jakarta")
public interface MoutonMapper {

    MoutonResponseDTO toResponse(Mouton mouton);

    @Mapping(target = "id", ignore = true)
    Mouton toEntity(CreateMoutonDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    void patch(PatchMoutonDTO dto, @MappingTarget Mouton mouton);
}