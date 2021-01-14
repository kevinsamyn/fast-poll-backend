package ovh.kevinsamyn.fastpoll.mappers

import ovh.kevinsamyn.fastpoll.dto.beans.AnswerChoiceDto
import ovh.kevinsamyn.fastpoll.jpa.entities.AnswerChoiceEntity

/**
 *
 */
object AnswerChoiceMapper : AbstractBaseMapper<AnswerChoiceEntity, AnswerChoiceDto, AnswerChoiceMapper.Options>() {
    override
    fun mapMainFields(source: AnswerChoiceEntity): AnswerChoiceDto {
        return AnswerChoiceDto(
                id = source.id,
                label = source.label
        )
    }

    override
    fun AnswerChoiceDto.mapOptionalsFields(source: AnswerChoiceEntity, options: Options): AnswerChoiceDto {

        if(null != options.checkBy){
            this.check = false  // init a false, calcul de la valeur plus tard
        }

        if(options.percent){
            this.percent = 0  // init a 0.0, calcul de la valeur plus tard
        }

        return this
    }

    class Options(pCheckBy: Long?, pPercent: Boolean) {
        var checkBy = pCheckBy
        var percent = pPercent
    }
}



