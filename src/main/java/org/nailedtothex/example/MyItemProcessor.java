package org.nailedtothex.example;

import javax.batch.api.chunk.ItemProcessor;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyItemProcessor implements ItemProcessor {
    private static final Logger log = Logger.getLogger(MyItemProcessor.class.getName());

    @Override
    public Object processItem(Object item) throws Exception {
        Integer original = (Integer) item;
        Integer processed = original * 100;
        log.log(Level.INFO, "original={0}, processed={1}", new Object[]{original, processed});
        return processed;
    }
}
