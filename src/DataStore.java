import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// okay so im trying to make sense of this right now, so a datastore is just a generic
// wrapper for a collection of item
// question to ask: is it okay to use ArrayList in the DataStore class or do we need to manually implement our
// own add methods for the collection.
public class DataStore<T extends Item> implements Iterable<T> {
    private ArrayList<T> items;
    public DataStore(ArrayList<T> tArrayList) {
        this.items = tArrayList;
    }
    public DataStore() {
        this.items = new ArrayList<T>();
    }
    public void add(T item) {
        items.add(item);
    }
    public T findById(int id) {
        // traverse, looking for id
        for (T item : items) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null; // item not found
    }
    public List<T> getAll() {
        return List.copyOf(items);
    }
    public void clear() {
        this.items.clear();
    }

    // override to allow for the iteration over the collection
    @Override
    public Iterator<T> iterator() {
        return items.iterator();
    }
}
