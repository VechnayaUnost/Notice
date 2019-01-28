package by.darya.zdzitavetskaya.notice.common.interfaces;

public interface Listener {

    void onItemClick(String id);

    void addToSolved(String id, int position);
}
