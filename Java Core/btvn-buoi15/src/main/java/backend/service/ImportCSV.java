package backend.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public interface ImportCSV<T, K> {
    void saveAll(List<T> entities);
    void exportFile(String header, List<String> dataErrors, String pathErr);
    void validation(String line, List<T> entities, List<String> dataErrors, K context);

    default String importCSV(String path, String pathErr, K context) {
        File file = new File(path);
        if (!path.endsWith(".csv")) {
            return "File ko đúng định dạng!";
        }
        if (!file.exists()) {
            return "File ko tồn tại!";
        }

        List<String> dataErrors = new ArrayList<>();
        List<T> entities = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String header = br.readLine();
            String line = null;

            while ((line = br.readLine()) != null) {
                this.validation(line, entities, dataErrors, context);
            }

            if (!entities.isEmpty()) {
                this.saveAll(entities);
            }

            if (!dataErrors.isEmpty()) {
                this.exportFile(header, dataErrors, pathErr);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Import thất bại";
        }

        return String.format("Import thành công %d, import thất bại %d, %s",
                entities.size(), dataErrors.size(),
                !dataErrors.isEmpty() ? "Đã xuất ra file lỗi " + pathErr : "");
    }
}