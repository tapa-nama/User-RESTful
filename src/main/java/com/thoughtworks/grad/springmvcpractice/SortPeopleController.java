package com.thoughtworks.grad.springmvcpractice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@RestController
public class SortPeopleController {
    @GetMapping("/api/people")
    public Map<String, Person> getSortedPeople(@RequestParam(name = "names") List<String> names,
                                               @RequestParam(name = "ages") List<Integer> ages) {
        return IntStream.range(0, names.size()).mapToObj(i -> new Person(names.get(i), ages.get(i))).sorted()
                .collect(LinkedHashMap::new, (map, person) -> map.put(person.getName(), person), Map::putAll);
    }
}
