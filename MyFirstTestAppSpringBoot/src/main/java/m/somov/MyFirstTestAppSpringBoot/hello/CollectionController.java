package m.somov.MyFirstTestAppSpringBoot.hello;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CollectionController {
  private ArrayList<String> arrayList;
  private Map<Integer, String> hashMap;
  private int mapKeyCounter = 0;

  @GetMapping("/update-array")
  public String updateArrayList(@RequestParam(required = true) String value) {
    if (arrayList == null) {
      arrayList = new ArrayList<String>();
      return "Created ArrayList";
    }

    arrayList.add(value);
    return String.format("Add %s", value);
  }

  @GetMapping("/show-array")
  public ArrayList<String> showArrayList() {
    return arrayList;
  }

  @GetMapping("/update-map")
  public String updateHashMap(@RequestParam(required = true) String value) {
    if (hashMap == null) {
      hashMap = new HashMap<Integer, String>();
      return "Created HashMap";
    }

    hashMap.put(mapKeyCounter++, value);
    return String.format("Add %s", value);
  }

  @GetMapping("/show-map")
  public Map<Integer, String> showHashMap() {
    return hashMap;
  }

  @GetMapping("/show-all-length")
  public String showAllLength() {
    Integer arraySize = arrayList == null ? 0 : arrayList.size();
    Integer mapSize = hashMap == null ? 0 : hashMap.size();
    return "ArrayList size: " + arraySize + ", HashMap size: " + mapSize;
  }
}
