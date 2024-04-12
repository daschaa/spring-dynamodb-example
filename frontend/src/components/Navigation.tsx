import {Box, Flex, Link} from "@chakra-ui/react";


const FlagIcon = () => (
  <svg
    xmlns="http://www.w3.org/2000/svg"
    width="24"
    height="24"
    viewBox="0 0 24 24"
    fill="none"
    stroke="currentColor"
    strokeWidth="2"
    strokeLinecap="round"
    strokeLinejoin="round"
  >
    <path d="M4 15s1-1 4-1 5 2 8 2 4-1 4-1V3s-1 1-4 1-5-2-8-2-4 1-4 1z"/>
    <line x1="4" x2="4" y1="22" y2="15"/>
  </svg>
);

export const Navigation = (props: { color: string }) => <Box as="header" w="full" py={{base: 4, md: 6}}>
  <Flex mx="auto" maxW="container.xl" px={4} align="center" justify="space-between" color={props.color}>
    <Link href="#" style={{display: "flex", alignItems: "center", gap: 2}} fontWeight="bold">
      <FlagIcon/>
      Home
    </Link>
    <Flex gap={4}>
      <Link href="/" fontWeight="medium">Overview</Link>
      <Link href="/submit" fontWeight="medium">Submit a Technique</Link>
    </Flex>
  </Flex>
</Box>;
