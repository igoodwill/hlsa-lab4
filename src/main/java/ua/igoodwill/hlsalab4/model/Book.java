package ua.igoodwill.hlsalab4.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Book implements Serializable {

    private static long serialVersionUID = 2097701461302174693L;

    @Id
    private String id;

    private String name;

    private String author;

    private String content;
}
