package tda.darkarmy.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tda.darkarmy.redditclone.model.VoteType;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class VoteDto {
    private VoteType voteType;
    private Long postId;
}
