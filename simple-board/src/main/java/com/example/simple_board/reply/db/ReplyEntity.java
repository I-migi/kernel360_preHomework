package com.example.simple_board.reply.db;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "reply")
public class ReplyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long postId;

    private String username;

    private String password;

    private String status;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private LocalDateTime repliedAt;
}
