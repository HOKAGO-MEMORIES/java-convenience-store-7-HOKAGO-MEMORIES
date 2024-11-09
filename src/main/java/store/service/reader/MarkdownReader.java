package store.service.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import store.domain.constant.Resource;
import store.util.ExceptionMessages;

public class MarkdownReader {
    public List<String> readResource(Resource resource) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream(resource.getFileName()))))) {
            // 첫 번째 줄 건너뜀
            reader.readLine();
            reader.lines().forEach(lines::add);
        } catch (IOException e) {
            throw new RuntimeException(ExceptionMessages.FILE_NOT_FOUND.getMessage(resource.getFileName()));
        }
        return lines;
    }
}
