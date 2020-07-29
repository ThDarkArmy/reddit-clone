package tda.darkarmy.redditclone.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CommentsDto {
    private Long id;
    private Long postId;
    private String text;
    private String username;
    private Instant createdDate;
}
