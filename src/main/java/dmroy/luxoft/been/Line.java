/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dmroy.luxoft.been;

import java.util.Objects;

/**
 *
 * @author dmitriyroy
 */
public class Line {
    private String fileName;
    private int lineNumber;
    private String maxWord;
    private String minWord;
    private int minWordLength;
    private int maxWordLength;
    private int averageWordLength;
    private long allWordsLength;
    private int wordsCount;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
       
    public int getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    
    public String getMaxWord() {
        return maxWord;
    }

    public void setMaxWord(String maxWord) {
        this.maxWord = maxWord;
    }

    public String getMinWord() {
        return minWord;
    }

    public void setMinWord(String minWord) {
        this.minWord = minWord;
    }

    public int getMinWordLength() {
        return minWordLength;
    }

    public void setMinWordLength(int minWordLength) {
        this.minWordLength = minWordLength;
    }

    public int getMaxWordLength() {
        return maxWordLength;
    }

    public void setMaxWordLength(int maxWordLength) {
        this.maxWordLength = maxWordLength;
    }

    public int getAverageWordLength() {
        return averageWordLength;
    }

    public void setAverageWordLength(int averageWordLength) {
        this.averageWordLength = averageWordLength;
    }

    public long getAllWordsLength() {
        return allWordsLength;
    }

    public void setAllWordsLength(long allWordsLength) {
        this.allWordsLength = allWordsLength;
    }

    public int getWordsCount() {
        return wordsCount;
    }

    public void setWordsCount(int wordsCount) {
        this.wordsCount = wordsCount;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 31 * hash + Objects.hashCode(this.fileName);
        hash = 31 * hash + this.lineNumber;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Line other = (Line) obj;
        if (this.lineNumber != other.lineNumber) {
            return false;
        }
        if (!Objects.equals(this.fileName, other.fileName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Line{" + "fileName=" + fileName + ", lineNumber=" + lineNumber + ", maxWord=" + maxWord + ", minWord=" + minWord + ", minWordLength=" + minWordLength + ", maxWordLength=" + maxWordLength + ", averageWordLength=" + averageWordLength + ", wordsLength=" + allWordsLength + ", wordsCount=" + wordsCount + '}';
    }    
    
    
}
