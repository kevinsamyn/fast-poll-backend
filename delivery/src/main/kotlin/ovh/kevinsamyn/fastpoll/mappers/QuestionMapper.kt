package ovh.kevinsamyn.fastpoll.mappers

import ovh.kevinsamyn.fastpoll.dto.beans.AnswerChoiceDto
import ovh.kevinsamyn.fastpoll.dto.beans.QuestionDto
import ovh.kevinsamyn.fastpoll.jpa.entities.QuestionEntity

/**
 *
 */
object QuestionMapper : AbstractBaseMapper<QuestionEntity, QuestionDto, QuestionMapper.Options>() {
    override
    fun mapMainFields(source: QuestionEntity): QuestionDto {
        return QuestionDto(
                id = source.id,
                label = source.label
        )
    }

    override
    fun QuestionDto.mapOptionalsFields(source: QuestionEntity, options: Options): QuestionDto {

        if (options.answersCount) {
            this.answersCount = 0
        }

        if (options.choices) {
            val choices = mutableListOf<AnswerChoiceDto>()
            for (choice in source.choices) {
                val choiceDto = AnswerChoiceMapper.map(choice, options.choicesOptions)
                choices.add(choiceDto)

            }
            this.choices = choices
        }
        return this
    }

    class Options {
        var choices = false
        var choicesOptions: AnswerChoiceMapper.Options? = null

        var answersCount = false
    }
}



