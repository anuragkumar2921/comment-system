package com.healthbrahmi.commentsystem.enums;

/**
 * Created by anurag on 11/3/21.
 */
public enum SortType {
    OLDEST_FIRST {
        public String toString() {
            return "OLDEST_FIRST";
        }
    },
    NEWEST_FIRST {
        public String toString() {
            return "NEWEST_FIRST";
        }
    },
}
