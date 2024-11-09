package store.service.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import store.domain.constant.Resource;
import store.util.ExceptionMessages;

public class MarkdownReader {
    public List<String> readResource(Resource resource) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(resource.getFilePath()))) {
            // 첫 번째 줄 건너뜀
            reader.readLine();
            reader.lines().forEach(lines::add);
        } catch (IOException e) {
            throw new RuntimeException(ExceptionMessages.FILE_NOT_FOUND.getMessage());
        }
        return lines;
    }
}
