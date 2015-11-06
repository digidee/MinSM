package rest.hello.org.resttest;

/**
 * Created by digi on 06.11.2015.
 */
public class Object_TestJSON {

    private String JSONobject = "{\n" +
            "  \"@count\": 10,\n" +
            "  \"@start\": 1,\n" +
            "  \"@totalcount\": 133,\n" +
            "  \"Messages\": [],\n" +
            "  \"ResourceName\": \"Incident\",\n" +
            "  \"ReturnCode\": 0,\n" +
            "  \"content\": [\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-printer-hr-5550\",\n" +
            "      \"Area\": \"failure\",\n" +
            "      \"Assignee\": \"Incident.Analyst\",\n" +
            "      \"AssignmentGroup\": \"Office Supplies (North America)\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"ClosedBy\": \"Incident.Analyst\",\n" +
            "      \"ClosedTime\": \"2007-09-01T01:13:00+00:00\",\n" +
            "      \"ClosureCode\": \"Solved by Workaround\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"Printjob keeps pending.\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10001\",\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-08-31T20:21:00+00:00\",\n" +
            "      \"OpenedBy\": \"Servicedesk.Manager\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"Printing (North America)\",\n" +
            "      \"Solution\": [\"Reset printer queue.\"],\n" +
            "      \"Status\": \"Closed\",\n" +
            "      \"Subarea\": \"job failed\",\n" +
            "      \"TicketOwner\": \"Servicedesk.Manager\",\n" +
            "      \"Title\": \"Printer malfunction\",\n" +
            "      \"UpdatedBy\": \"falcon\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T12:53:21+00:00\",\n" +
            "      \"Urgency\": \"3\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-server-mail\",\n" +
            "      \"Area\": \"failure\",\n" +
            "      \"AssignmentGroup\": \"E-mail / Webmail (North America)\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"Webmail can't be reached, browser can\\u2019t find it\"],\n" +
            "      \"Impact\": \"2\",\n" +
            "      \"IncidentID\": \"IM10002\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"08/04/08 12:54:14 US/Mountain (falcon):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 17:44:03 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:24:22 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:45:54 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 13:04:15 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"04/23/08 23:24:17 US/Mountain (falcon):\",\n" +
            "        \"yo yo\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-02T07:51:00+00:00\",\n" +
            "      \"OpenedBy\": \"Jurr.Fleijs\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"E-mail / Webmail (North America)\",\n" +
            "      \"Status\": \"Work In Progress\",\n" +
            "      \"Subarea\": \"function or feature not working\",\n" +
            "      \"TicketOwner\": \"Jurr.Fleijs\",\n" +
            "      \"Title\": \"Webmail login failure\",\n" +
            "      \"UpdatedBy\": \"falcon\",\n" +
            "      \"UpdatedTime\": \"2015-11-05T10:24:26+00:00\",\n" +
            "      \"Urgency\": \"3\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-lapt-111\",\n" +
            "      \"Area\": \"performance\",\n" +
            "      \"Assignee\": \"Incident.Coordinator\",\n" +
            "      \"AssignmentGroup\": \"Hardware\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"System crash with message not enough memory while opening multiple applications\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10003\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"08/04/08 12:55:11 US/Mountain (falcon):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 17:44:12 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:24:30 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:46:13 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-03T03:49:00+00:00\",\n" +
            "      \"OpenedBy\": \"Sylvia.White\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"MyDevices\",\n" +
            "      \"Status\": \"Work In Progress\",\n" +
            "      \"Subarea\": \"performance degradation\",\n" +
            "      \"TicketOwner\": \"Sylvia.White\",\n" +
            "      \"Title\": \"System crashes with message \\\"not enough memory\\\" while opening multiple applications\",\n" +
            "      \"UpdatedBy\": \"problem\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T12:55:26+00:00\",\n" +
            "      \"Urgency\": \"2\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-lapt-148\",\n" +
            "      \"Area\": \"failure\",\n" +
            "      \"Assignee\": \"Incident.Analyst\",\n" +
            "      \"AssignmentGroup\": \"Hardware\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"\\\"Wireless connects with message \\\"\\\"limited or no connectivity\\\"\\\"\\\"\"],\n" +
            "      \"Impact\": \"3\",\n" +
            "      \"IncidentID\": \"IM10004\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"08/04/08 12:55:27 US/Mountain (falcon):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 17:44:45 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 17:44:25 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:24:36 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:48:27 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-06T14:02:00+00:00\",\n" +
            "      \"OpenedBy\": \"Sylvia.White\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"MyDevices\",\n" +
            "      \"Status\": \"Open\",\n" +
            "      \"Subarea\": \"function or feature not working\",\n" +
            "      \"TicketOwner\": \"Sylvia.White\",\n" +
            "      \"Title\": \"Wireless doesn't connect\",\n" +
            "      \"UpdatedBy\": \"acorvino\",\n" +
            "      \"UpdatedTime\": \"2010-04-20T13:15:13+00:00\",\n" +
            "      \"Urgency\": \"3\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"Microsoft Office 2007\",\n" +
            "      \"Area\": \"failure\",\n" +
            "      \"Assignee\": \"Incident.Manager\",\n" +
            "      \"AssignmentGroup\": \"Application\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"Microsoft Office keeps asking to install Language packs\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10005\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"08/04/08 12:55:38 US/Mountain (falcon):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 17:45:07 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:24:42 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:48:43 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-06T21:57:00+00:00\",\n" +
            "      \"OpenedBy\": \"Sylvia.White\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"Applications\",\n" +
            "      \"Status\": \"Work In Progress\",\n" +
            "      \"Subarea\": \"error message\",\n" +
            "      \"TicketOwner\": \"Sylvia.White\",\n" +
            "      \"Title\": \"Microsoft Office keeps asking to install Language packs\",\n" +
            "      \"UpdatedBy\": \"problem\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T12:55:42+00:00\",\n" +
            "      \"Urgency\": \"4\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-desk-169\",\n" +
            "      \"Area\": \"performance\",\n" +
            "      \"Assignee\": \"Incident.Coordinator\",\n" +
            "      \"AssignmentGroup\": \"Hardware\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"Everyday around lunch time my PC starts rebooting for no apparent reason. Can you please check what's wrong? I get an error message about CPU temperature.\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10006\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"08/04/08 12:55:45 US/Mountain (falcon):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 17:45:13 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:24:48 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:48:53 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-06T22:05:00+00:00\",\n" +
            "      \"OpenedBy\": \"Jurr.Fleijs\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"MyDevices\",\n" +
            "      \"Status\": \"Work In Progress\",\n" +
            "      \"Subarea\": \"performance degradation\",\n" +
            "      \"TicketOwner\": \"Jurr.Fleijs\",\n" +
            "      \"Title\": \"Pop-up appears while working with Office, Office needs installation of additional components\",\n" +
            "      \"UpdatedBy\": \"problem\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T12:55:57+00:00\",\n" +
            "      \"Urgency\": \"2\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-server-mail\",\n" +
            "      \"Area\": \"failure\",\n" +
            "      \"Assignee\": \"Incident.Manager\",\n" +
            "      \"AssignmentGroup\": \"Network\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"E-mail in outbox won't go out\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10007\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"07/23/08 17:45:19 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:24:54 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:49:03 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-06T23:05:00+00:00\",\n" +
            "      \"OpenedBy\": \"Jurr.Fleijs\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"E-mail / Webmail (North America)\",\n" +
            "      \"Status\": \"Open\",\n" +
            "      \"Subarea\": \"job failed\",\n" +
            "      \"TicketOwner\": \"Jurr.Fleijs\",\n" +
            "      \"Title\": \"E-mail in outbox isn't beeing sent\",\n" +
            "      \"UpdatedBy\": \"problem\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T12:59:43+00:00\",\n" +
            "      \"Urgency\": \"1\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-desk-202\",\n" +
            "      \"Area\": \"hardware\",\n" +
            "      \"Assignee\": \"Incident.Coordinator\",\n" +
            "      \"AssignmentGroup\": \"Hardware\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"Desktop DVD-drive makes strange noices\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10008\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"07/23/08 17:45:25 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:25:01 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:50:41 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-07T00:14:00+00:00\",\n" +
            "      \"OpenedBy\": \"Sylvia.White\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"MyDevices\",\n" +
            "      \"Status\": \"Work In Progress\",\n" +
            "      \"Subarea\": \"hardware failure\",\n" +
            "      \"TicketOwner\": \"Sylvia.White\",\n" +
            "      \"Title\": \"Desktop DVD-drive makes strange noices\",\n" +
            "      \"UpdatedBy\": \"problem\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T13:00:28+00:00\",\n" +
            "      \"Urgency\": \"3\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-desk-211\",\n" +
            "      \"Area\": \"hardware\",\n" +
            "      \"Assignee\": \"Incident.Coordinator\",\n" +
            "      \"AssignmentGroup\": \"Hardware\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"Desktop screen out of order\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10009\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"07/23/08 17:45:31 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:25:18 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:50:47 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-07T00:37:00+00:00\",\n" +
            "      \"OpenedBy\": \"Servicedesk.Manager\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"MyDevices\",\n" +
            "      \"Status\": \"Work In Progress\",\n" +
            "      \"Subarea\": \"hardware failure\",\n" +
            "      \"TicketOwner\": \"Servicedesk.Manager\",\n" +
            "      \"Title\": \"Desktop screen out of order\",\n" +
            "      \"UpdatedBy\": \"problem\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T13:00:28+00:00\",\n" +
            "      \"Urgency\": \"1\"\n" +
            "    }},\n" +
            "    {\"Incident\": {\n" +
            "      \"AffectedCI\": \"adv-nam-server-web\",\n" +
            "      \"Area\": \"access\",\n" +
            "      \"Assignee\": \"Incident.Manager\",\n" +
            "      \"AssignmentGroup\": \"Network\",\n" +
            "      \"Category\": \"incident\",\n" +
            "      \"Company\": \"advantage\",\n" +
            "      \"Description\": [\"User cannot logon to network\"],\n" +
            "      \"Impact\": \"4\",\n" +
            "      \"IncidentID\": \"IM10010\",\n" +
            "      \"JournalUpdates\": [\n" +
            "        \"07/23/08 17:45:37 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 16:25:24 US/Mountain (prietke):\",\n" +
            "        \"test\",\n" +
            "        \"07/23/08 14:50:53 US/Mountain (prietke):\",\n" +
            "        \"test\"\n" +
            "      ],\n" +
            "      \"Location\": \"advantage/North America\",\n" +
            "      \"OpenTime\": \"2007-09-07T01:52:00+00:00\",\n" +
            "      \"OpenedBy\": \"Jurr.Fleijs\",\n" +
            "      \"ProblemType\": \"incident\",\n" +
            "      \"SLAAgreementID\": 168,\n" +
            "      \"Service\": \"Intranet / Internet (North America)\",\n" +
            "      \"Status\": \"Accepted\",\n" +
            "      \"Subarea\": \"login failure\",\n" +
            "      \"TicketOwner\": \"Jurr.Fleijs\",\n" +
            "      \"Title\": \"Network logon failure\",\n" +
            "      \"UpdatedBy\": \"falcon\",\n" +
            "      \"UpdatedTime\": \"2008-08-04T13:00:31+00:00\",\n" +
            "      \"Urgency\": \"1\"\n" +
            "    }}\n" +
            "  ]\n" +
            "}";


    String getJSONObject(){
        return JSONobject;
    }


}
