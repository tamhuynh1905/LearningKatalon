<?xml version="1.0" encoding="UTF-8"?>
<TestSuiteEntity>
   <description></description>
   <name>TS for learning</name>
   <tag></tag>
   <isRerun>false</isRerun>
   <mailRecipient></mailRecipient>
   <numberOfRerun>3</numberOfRerun>
   <pageLoadTimeout>30</pageLoadTimeout>
   <pageLoadTimeoutDefault>true</pageLoadTimeoutDefault>
   <rerunFailedTestCasesOnly>false</rerunFailedTestCasesOnly>
   <rerunImmediately>true</rerunImmediately>
   <testSuiteGuid>f1b5fbbc-3d5e-427e-90dd-85b9ca319b84</testSuiteGuid>
   <testCaseLink>
      <guid>ad0e7460-7547-42a6-a507-4a59fc4152ee</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/Common Test Cases/TestLogin</testCaseId>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
   </testCaseLink>
   <testCaseLink>
      <guid>7f3418b8-d870-4d11-a963-14add0fd921b</guid>
      <isReuseDriver>false</isReuseDriver>
      <isRun>true</isRun>
      <testCaseId>Test Cases/API/API-001-Create User</testCaseId>
      <testDataLink>
         <combinationType>ONE</combinationType>
         <id>e6b2a93a-a353-4ebd-ad77-c5cb84dd8bde</id>
         <iterationEntity>
            <iterationType>ALL</iterationType>
            <value></value>
         </iterationEntity>
         <testDataId>Data Files/API/User</testDataId>
      </testDataLink>
      <usingDataBindingAtTestSuiteLevel>true</usingDataBindingAtTestSuiteLevel>
      <variableLink>
         <testDataLinkId>e6b2a93a-a353-4ebd-ad77-c5cb84dd8bde</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>username</value>
         <variableId>10996c35-2f43-40ac-99c2-1aa50eeb21de</variableId>
      </variableLink>
      <variableLink>
         <testDataLinkId>e6b2a93a-a353-4ebd-ad77-c5cb84dd8bde</testDataLinkId>
         <type>DATA_COLUMN</type>
         <value>password</value>
         <variableId>9c7146c5-6907-4814-a1a3-d1009ad2b22a</variableId>
      </variableLink>
   </testCaseLink>
</TestSuiteEntity>
