package com.example.sampleurl_shortener.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(
        name = "sus_url",
        indexes = @Index(name = "ix_url_a_url_key", columnList = "url_key", unique = true)
)
//@Table(name = "sus_url")
@NamedQueries({
        @NamedQuery(name = "SUSUrl.findAll", query = "SELECT p FROM SUSUrl p")})
public class SUSUrl implements Serializable
{

    private static final long serialVersionUID = 733965560945383706L;

    @Id
    @Basic(optional = false)
    @Column(name = "url_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long urlId;

    @Basic(optional = false)
    @Column(name = "url_key", length = 10, unique = true)
    private String urlKey;

    @Lob
    @Basic(optional = false)
    @Column(name = "url")
    private String url;

    @Basic(optional = false)
    @Column (name = "url_update_status")
    private Integer urlUpdateStatus;

    @Basic(optional = false)
    @Column (name = "url_delete_status")
    private Integer urlDeleteStatus;

    @Basic(optional = false)
    @Column (name = "url_expire_status")
    private Integer urlExpireStatus;

    @Column (name = "url_expire_date_time")
    private Timestamp urlExpireDateTime;

    @Column (name = "url_delete_date_time")
    private Timestamp urlDeleteDateTime;

    @Basic(optional = false)
    @Column (name = "raw_created_date_time")
    private Timestamp rawCreateDateTime;

    @Basic(optional = false)
    @Column (name = "raw_last_update_date_time")
    private Timestamp rawLastUpdateDateTime;

    @Basic(optional = false)
    @Column (name = "raw_active_status")
    private Integer rawActiveStatus;

    @Basic(optional = false)
    @Column (name = "url_hit")
    private Integer urlHit;
}
