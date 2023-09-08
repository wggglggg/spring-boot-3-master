package com.sgg.springboot3.boot;

import com.sgg.springboot3.boot.record.AlbumsRecord;
import com.sgg.springboot3.boot.service.AlbumsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName: AlbumsTest
 * Description:
 *
 * @Author wggglggg
 * @Create 2023-09-08 16:37
 * @Version 1.0
 */
@SpringBootTest
public class AlbumsTest {

    @Autowired
    private AlbumsService albumsService;

    @Test
    public void testGetAlbumsById(){
        AlbumsRecord albums = albumsService.getAlbumsById(10);

        System.out.println("albums = " + albums);
    }

    @Test
    public void testCreateNewAlbums(){
        AlbumsRecord albumsRecord = new AlbumsRecord(66, 4, "年轻的我们");

        AlbumsRecord newAlbums = albumsService.createNewAlbums(albumsRecord);

        System.out.println("newAlbums = " + newAlbums);
    }

    @Test
    public void testEditAlbums(){
        AlbumsRecord albumsRecord = new AlbumsRecord(66, 4, "快到这里来");
        AlbumsRecord albums = albumsService.editAlbums(4, albumsRecord);

        System.out.println("albums = " + albums);
    }

    @Test
    public void testDeleteAlbumsById(){
        albumsService.deleteAlbumsById(4);

        System.out.println("删除完成");
    }
}
