package LLDesigns;
//Implement a Log Aggregation System which aggregates logs from various services in a datacenter and provides search APIs.
//
//Design the LogAggregator class:
//
//LogAggregator(int machines, int services) Initializes the object with machines and services representing the number of machines and services in the datacenter, respectively.
//void pushLog(int logId, int machineId, int serviceId, String message) Adds a log with id logId notifying that the machine machineId sent a string message while executing the service serviceId.
//List<Integer> getLogsFromMachine(int machineId) Returns a list of ids of all logs added by machine machineId.
//List<Integer> getLogsOfService(int serviceId) Returns a list of ids of all logs added while running service serviceId on any machine.
//List<String> search(int serviceId, String searchString) Returns a list of messages of all logs added while running service serviceId where the message of the log contains searchString as a substring.

//notes
// The entries in each list should be in the order they were added, i.e., the older logs should precede the newer logs.
//A machine can run multiple services more than once. Similarly, a service can be run on multiple machines.
//All logId may not be ordered.
//A substring is a contiguous sequence of characters within a string.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class LogAggregationSystem {
    Map<Integer, List<Integer>> machineMessages; // mackineId and list of logIds
    Map<Integer, List<Integer>> serviceMessages; // serviceId and list of logIds
    Map<Integer, String>        logs;

    public LogAggregationSystem() {
        machineMessages = new HashMap<>();
        serviceMessages = new HashMap<>();
        logs = new HashMap<>();
    }

    public List<Integer> getLogsFromMachine(int machineId) {
        return machineMessages.getOrDefault(machineId, new ArrayList());
    }

    public List<Integer> getLogsOfService(int serviceId) {
        return serviceMessages.getOrDefault(serviceId, new ArrayList());
    }

    public List<String> search(int serviceId, String searchString) {
        List<String> res = new ArrayList<String>();

        for(Integer log : serviceMessages.getOrDefault(serviceId, new ArrayList<Integer>())) {
            if(logs.get(log).contains(searchString))
                res.add(logs.get(log));
        }
        return res;
    }

}

/**
 * Your LogAggregator object will be instantiated and called as such:
 * LogAggregator obj = new LogAggregator();
 * obj.pushLog(logId,machineId,serviceId,message);
 * List<Integer> param_2 = obj.getLogsFromMachine(machineId);
 * List<Integer> param_3 = obj.getLogsOfService(serviceId);
 * List<String> param_4 = obj.search(serviceId,searchString);
 */