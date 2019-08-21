package ua.com.tascombank.ibanupdater;

public class IbanStore {
    private String id;
    private String mfo;
    private String acc;

    public String getId() {
        return id;
    }

    public IbanStore setId(String id) {
        this.id = id;
        return this;
    }

    public String getMfo() {
        return mfo;
    }

    public IbanStore setMfo(String mfo) {
        this.mfo = mfo;
        return this;
    }

    public String getAcc() {
        return acc;
    }

    public IbanStore setAcc(String acc) {
        this.acc = acc;
        return this;
    }

    @Override
    public String toString() {
        return "IbanStore{" +
                "id='" + id + '\'' +
                ", mfo='" + mfo + '\'' +
                ", acc='" + acc + '\'' +
                '}';
    }
}
