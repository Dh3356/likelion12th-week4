package org.mjulikelion.likelion12thserver.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
public class User extends Entity {
    private String name;
}
