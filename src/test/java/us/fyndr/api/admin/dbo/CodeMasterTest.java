package us.fyndr.api.admin.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CodeMasterTest {

    @Test
    public void testGetterAndSetter() {

        CodeMaster codeMaster = new CodeMaster();

        assertNull(codeMaster.getObjId());
        Long objId = 4L;
        codeMaster.setObjId(4L);
        assertEquals(objId, codeMaster.getObjId());
    }

    @Test
    public void testToString() {
        CodeMaster codeMaster = new CodeMaster();

        Long objId = 4L;
        codeMaster.setObjId(objId);

        assertEquals("CodeMaster [objId=4]", codeMaster.toString());

    }

    @Test
    public void testEqualsAndHashcode() {
        Long objId = 4L;

        CodeMaster codeMaster1 = new CodeMaster();
        codeMaster1.setObjId(objId);

        assertEquals(codeMaster1, codeMaster1);
        assertEquals(codeMaster1.hashCode(), codeMaster1.hashCode());
        assertNotEquals(codeMaster1, new Object());

        CodeMaster codeMaster2 = new CodeMaster();
        codeMaster2.setObjId(objId);
        assertEquals(codeMaster1, codeMaster2);
        assertEquals(codeMaster1.hashCode(), codeMaster2.hashCode());

        codeMaster2.setObjId(2L);
        assertNotEquals(codeMaster1, codeMaster2);
        assertNotEquals(codeMaster1.hashCode(), codeMaster2.hashCode());

        codeMaster1 = new CodeMaster();
        codeMaster2 = new CodeMaster();
        assertEquals(codeMaster1, codeMaster2);
        assertEquals(codeMaster1.hashCode(), codeMaster2.hashCode());

    }
}
