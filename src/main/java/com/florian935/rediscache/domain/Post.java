package com.florian935.rediscache.domain;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor
@AllArgsConstructor
@Data
@FieldDefaults(level = PRIVATE)
@Builder
@NotNull
@Entity(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    Long id;

    @NotEmpty
    @Column(nullable = false)
    String title;

    @NotEmpty
    @Column(nullable = false)
    String description;

    @NotEmpty
    @Column(nullable = false)
    String author;
}
