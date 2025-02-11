package management.sedef.issue.port.issuePort;

import management.sedef.issue.model.Issue;

public interface IssueSavePort {
    Issue save(Issue issue);
}
