package com.example.travel_journal.async;

import com.example.travel_journal.R;

public interface Callback<R> {
    void runResultOnUIThread(R result);
}
