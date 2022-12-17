package pl.lssystems.adventofcode.v2022.day16;

import java.util.List;
import java.util.Objects;

public class Valve {
    private final String id;
    private final long flow;
    private final List<String> routes;

    public Valve(String id, int flow, List<String> routes) {
        this.id = id;
        this.flow = flow;
        this.routes = routes;
    }

    public String getId() {
        return id;
    }

    public long getFlow() {
        return flow;
    }

    public List<String> getRoutes() {
        return routes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Valve valve = (Valve) o;
        return flow == valve.flow && Objects.equals(id, valve.id) && Objects.equals(routes, valve.routes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, flow, routes);
    }

    @Override
    public String toString() {
        return "Valve{" +
                "id='" + id + '\'' +
                ", flow=" + flow +
                ", others=" + routes +
                '}';
    }

}