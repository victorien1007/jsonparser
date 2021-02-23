

import model.JsonArray;
import model.JsonObject;
import util.JSONParser;

import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JSONParserTest {

    @Test
    @Ignore
    public void fromJSON() throws Exception {
        String path = this.getClass().getResource("/music.json").getFile();
        String json = new String(Files.readAllBytes(Paths.get(path)), "utf-8");
//       String json = getJSON();

        JSONParser jsonParser = new JSONParser();
        JsonObject jsonObject = (JsonObject) jsonParser.fromJSON(json);
        System.out.println(jsonObject);
    }

    @Test
    public void fromJSON1() throws Exception {
        String json = "{\"a\": 1, \"b\": \"b\", \"c\": {\"a\": 1, \"b\": null, \"d\": [0.1, \"a\", 1,2, 123, 1.23e+10, true, false, null]}}";
        JSONParser jsonParser = new JSONParser();
        JsonObject jsonObject = (JsonObject) jsonParser.fromJSON(json);
        System.out.println(jsonObject);

        assertEquals(1, jsonObject.get("a"));
        assertEquals("b", jsonObject.get("b"));

        JsonObject c = jsonObject.getJsonObject("c");
        assertEquals(null, c.get("b"));

        JsonArray d = c.getJsonArray("d");
        assertEquals(0.1, d.get(0));
        assertEquals("a", d.get(1));
        assertEquals(123, d.get(4));
        assertEquals(1.23e+10, d.get(5));
        assertTrue((Boolean) d.get(6));
        assertFalse((Boolean) d.get(7));
        assertEquals(null, d.get(8));
    }

    @Test
    public void fromJSON2() throws Exception {
        String json = "[[1,2,3,\"\u4e2d\"]]";
        JSONParser jsonParser = new JSONParser();
        JsonArray jsonArray = (JsonArray) jsonParser.fromJSON(json);
        System.out.println(jsonArray);
    }

    @Test
    public void beautifyJSON() throws Exception {
        String json = "{\"name\": \"狄仁杰\", \"type\": \"射手\", \"ability\":[\"六令追凶\",\"逃脱\",\"王朝密令\"],\"history\":{\"DOB\":630,\"DOD\":700,\"position\":\"宰相\",\"dynasty\":\"唐朝\"}}";
        System.out.println("原 JSON 字符串：");
        System.out.println(json);
        System.out.println("\n");
        System.out.println("美化后的 JSON 字符串：");
        JSONParser jsonParser = new JSONParser();
        JsonObject drj = (JsonObject) jsonParser.fromJSON(json);
        System.out.println(drj);
    }

}