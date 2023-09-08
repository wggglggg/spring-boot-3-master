package com.sgg.springboot3.boot.service;

import com.sgg.springboot3.boot.record.AlbumsRecord;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.DeleteExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;
import org.springframework.web.service.annotation.PutExchange;

/**
 * ClassName: AlbumsService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 16:21
 * @Version 1.0
 */
@HttpExchange(url = "https://jsonplaceholder.typicode.com")
public interface AlbumsService {

    @HttpExchange(method = "GET", url = "/albums/{id}")
    public AlbumsRecord getAlbumsById(@PathVariable Integer id);

    @PostExchange(url = "/albums", accept = MediaType.APPLICATION_JSON_VALUE)
    public AlbumsRecord createNewAlbums(@RequestBody  AlbumsRecord albumsRecord);

    @PutExchange(url = "/albums/{id}")
    public AlbumsRecord editAlbums(@PathVariable Integer id, @RequestBody AlbumsRecord albumsRecord);

    @HttpExchange(method = "DELETE", url = "/albums/{id}")
    public AlbumsRecord deleteAlbumsById(@PathVariable Integer id);

}
