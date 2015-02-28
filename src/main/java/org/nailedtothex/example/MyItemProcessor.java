package org.nailedtothex.example;

import javax.batch.api.chunk.ItemProcessor;
import java.util.Arrays;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyItemProcessor implements ItemProcessor {
    private static final Logger log = Logger.getLogger(MyItemProcessor.class.getName());

    @Override
    public Object processItem(Object item) throws Exception {
        Map map = (Map) item;
        final Object data = map.get("DATA");
        Integer original = (Integer) data;
        Integer processed = original * 100;
        log.log(Level.INFO, "original={0}, processed={1}", new Object[]{original, processed});
        return Arrays.asList(processed);
    }
}
