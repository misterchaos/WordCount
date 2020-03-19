package sample.model;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * @author <a href="mailto:kobe524348@gmail.com">黄钰朝</a>
 * @description 用于存放表格数据
 * @date 2020-03-19 18:07
 */
public class TableItem {
    private SimpleIntegerProperty character = new SimpleIntegerProperty();
    private SimpleIntegerProperty word = new SimpleIntegerProperty();
    private SimpleIntegerProperty line = new SimpleIntegerProperty();
    private SimpleIntegerProperty emptyLine = new SimpleIntegerProperty();
    private SimpleIntegerProperty codeLine = new SimpleIntegerProperty();
    private SimpleIntegerProperty annotationLine = new SimpleIntegerProperty();
    private SimpleStringProperty file = new SimpleStringProperty();
    private SimpleStringProperty time = new SimpleStringProperty();
    private SimpleStringProperty length = new SimpleStringProperty();

    public int getCharacter() {
        return character.get();
    }

    public SimpleIntegerProperty characterProperty() {
        return character;
    }

    public void setCharacter(int character) {
        this.character.set(character);
    }

    public int getWord() {
        return word.get();
    }

    public SimpleIntegerProperty wordProperty() {
        return word;
    }

    public void setWord(int word) {
        this.word.set(word);
    }

    public int getLine() {
        return line.get();
    }

    public SimpleIntegerProperty lineProperty() {
        return line;
    }

    public void setLine(int line) {
        this.line.set(line);
    }

    public int getEmptyLine() {
        return emptyLine.get();
    }

    public SimpleIntegerProperty emptyLineProperty() {
        return emptyLine;
    }

    public void setEmptyLine(int emptyLine) {
        this.emptyLine.set(emptyLine);
    }

    public int getCodeLine() {
        return codeLine.get();
    }

    public SimpleIntegerProperty codeLineProperty() {
        return codeLine;
    }

    public void setCodeLine(int codeLine) {
        this.codeLine.set(codeLine);
    }

    public int getAnnotationLine() {
        return annotationLine.get();
    }

    public SimpleIntegerProperty annotationLineProperty() {
        return annotationLine;
    }

    public void setAnnotationLine(int annotationLine) {
        this.annotationLine.set(annotationLine);
    }

    public String getFile() {
        return file.get();
    }

    public SimpleStringProperty fileProperty() {
        return file;
    }

    public void setFile(String file) {
        this.file.set(file);
    }

    public String getTime() {
        return time.get();
    }

    public SimpleStringProperty timeProperty() {
        return time;
    }

    public void setTime(String time) {
        this.time.set(time);
    }

    public String getLength() {
        return length.get();
    }

    public SimpleStringProperty lengthProperty() {
        return length;
    }

    public void setLength(String length) {
        this.length.set(length);
    }
}